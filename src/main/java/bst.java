public class bst {
    static class node {
        int key;
        node left, right;
        node(int k) { key = k; }
    }

    node root;

    void insert(int k) {
        if (root == null) { root = new node(k); return; }
        node cur = root;
        while (true) {
            if (k == cur.key) return;
            if (k < cur.key) {
                if (cur.left == null) { cur.left = new node(k); return; }
                cur = cur.left;
            } else {
                if (cur.right == null) { cur.right = new node(k); return; }
                cur = cur.right;
            }
        }
    }

    boolean search(int k) {
        node cur = root;
        while (cur != null) {
            if (k == cur.key) return true;
            cur = k < cur.key ? cur.left : cur.right;
        }
        return false;
    }

    void delete(int k) { root = delete(root, k); }

    private node delete(node n, int k) {
        if (n == null) return null;
        if (k < n.key) n.left = delete(n.left, k);
        else if (k > n.key) n.right = delete(n.right, k);
        else {
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;
            node m = n.right;
            while (m.left != null) m = m.left;
            n.key = m.key;
            n.right = delete(n.right, m.key);
        }
        return n;
    }
}