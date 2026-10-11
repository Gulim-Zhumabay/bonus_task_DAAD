public class hashtable {
    static class node {
        int key;
        node next;
        node(int k, node n) { key = k; next = n; }
    }

    node[] table = new node[16];
    int size = 0;

    int index(int k, int len) { return (k & 0x7fffffff) % len; }

    void insert(int k) {
        if (search(k)) return;
        if (size >= table.length * 3 / 4) resize();
        int i = index(k, table.length);
        table[i] = new node(k, table[i]);
        size++;
    }

    boolean search(int k) {
        node cur = table[index(k, table.length)];
        while (cur != null) {
            if (cur.key == k) return true;
            cur = cur.next;
        }
        return false;
    }

    void delete(int k) {
        int i = index(k, table.length);
        node cur = table[i], prev = null;
        while (cur != null) {
            if (cur.key == k) {
                if (prev == null) table[i] = cur.next;
                else prev.next = cur.next;
                size--;
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    void resize() {
        node[] old = table;
        table = new node[old.length * 2];
        for (node head : old) {
            for (node n = head; n != null; n = n.next) {
                int i = index(n.key, table.length);
                table[i] = new node(n.key, table[i]);
            }
        }
    }
}