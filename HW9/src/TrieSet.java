import sun.text.normalizer.Trie;

import java.util.*;

class TrieSet extends AbstractSet<String> {

    private boolean isWord;
    private int size;
    private TrieSet[] children;
    private static final int ARRAY_LENGTH = 26;

    public TrieSet() {
        this.isWord = false;
        this.size = 0;
        this.children = new TrieSet[ARRAY_LENGTH];
    }

    public boolean add(String s) {
        s = s.toLowerCase();

        if (s.length() == 0) {
            boolean isModified = !isWord;

            if (isModified) {
                isWord = true;
            }
            return isModified;
        }
        if (this.contains(s)) {
            return false;
        }

        this.size++;

        char c = s.charAt(0);
        if (!Character.isLetter(c)) {
            return false;
        }

        int value = letterToValue(c);

        if (children[value] == null) {
            children[value] = new TrieSet();
        }

        return children[value].add(s.substring(1));
    }

    @SuppressWarnings("unchecked")
    public boolean contains(Object obj) {
        try {
            String stringForm = (String) obj;
            stringForm = stringForm.toLowerCase();

            if (stringForm.length() == 0) {
                return this.isWord;
            }

            char c = stringForm.charAt(0);
            if (!Character.isLetter(c)) {
                return false;
            }
            int value = letterToValue(c);


            if (children[value] == null) {
                return false;
            }
            return children[value].contains(stringForm.substring(1));
        }
        catch (ClassCastException cce) {
            return false;
        }
    }

    public TrieSet subTrie(String prefix) {
        if (prefix.length() == 0) {
            return this;
        }

        prefix = prefix.toLowerCase();

        char c = prefix.charAt(0);
        if (!Character.isLetter(c)) {
            return null;
        }
        int value = letterToValue(c);
        if (children[value] == null) {
            return null;
        }

        return children[value].subTrie(prefix.substring(1));
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public int size() {
        return this.size;
    }

    private List<String> toList(List<String> list, String prefix) {
        if (this.isWord) {
            list.add(prefix);
        }
        if (this.isEmpty()) {
            return list;
        }

        for (int i = 0; i < ARRAY_LENGTH; i++) {
            char c = Character.toChars(i + 'a')[0];
            if (children[i] != null) {
                children[i].toList(list, prefix+c);
            }
        }
        return list;
    }

    public static int letterToValue(char letter) {
        return letter - 'a';
    }

    public List<String> toList() {
        return toList(new ArrayList<String>(), "");
    }

    public Iterator<String> iterator() {
        return toList().iterator();
    }

    public String toString() {
        return toList().toString();
    }

    public static void main(String[] args) {
        TrieSet ts = new TrieSet();
        ts.add("aba");
        ts.add("abal");
        ts.add("aca");
        ts.add("baa");
        System.out.println(ts.size());
        System.out.println(ts.contains("ab"));
        System.out.println(ts.toString());
    }

}