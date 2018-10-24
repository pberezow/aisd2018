public class BSTNode {
    BSTNode p;
    String key;
    BSTNode left;
    BSTNode right;

    public BSTNode(BSTNode parent, String key) {
        this.p = parent;
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public void insert(String key) {
        if(key.compareTo(this.key) < 0) {
            if(left != null) {
                left.insert(key);
            } else {
                left = new BSTNode(this, key);
            }
        } else {
            if(right != null) {
                right.insert(key);
            } else {
                right = new BSTNode(this, key);
            }
        }
    }

    public BSTNode delete(BSTNode root, String key) {
        if(root == null) {
            return root;
        }
        if(key.compareTo(this.key) < 0) {
            if(this.left != null) {
                return this.left.delete(root, key);
            } else {
                return root;
            }
        } else if(key.compareTo(this.key) > 0) {
            if(this.right != null) {
                return this.right.delete(root, key);
            } else {
                return root;
            }
        } else {
            if(this.p != null) {
                if(this.left == null) {
                    if(this.p.left == this) {
                        this.p.left = this.right;
                    } else {
                        this.p.right = this.right;
                    }
                    if(this.right != null) {
                        this.right.p = this.p;
                    }
                } else if(this.right == null) {
                    if(this.p.left == this) {
                        this.p.left = this.left;
                    } else {
                        this.p.right = this.left;
                    }
                    this.left.p = this.p;
                } else {
                    BSTNode next = this.right;
                    while(next.left != null) {
                        next = next.left;
                    }
                    if(next.p.left == next) {
                        next.p.left = next.right;
                    } else {
                        next.p.right = next.right;
                    }
                    if(next.right != null) {
                        next.right.p = next.p;
                    }
                    if(this.p.left == this) {
                        this.p.left = next;
                    } else {
                        this.p.right = next;
                    }
                    next.left = this.left;
                    next.right = this.right;
                    if(next.left != null) {
                        next.left.p = next;
                    }
                    if(next.right != null) {
                        next.right.p = next;
                    }
                    next.p = this.p;
                }
                return root;
            } else {
                BSTNode next = null;
                if(this.left == null) {
                    if(this.right != null) {
                        this.right.p = null;
                    }
                    return this.right;
                    // root = this.right;
                } else if(this.right == null) {
                    this.left.p = null;
                    return this.left;
                    // root = this.left;
                } else {
                    next = this.right;
                    while(next.left != null) {
                        next = next.left;
                    }
                    if(next.p.left == next) {
                        next.p.left = next.right;
                    } else {
                        next.p.right = next.right;
                    }
                    if(next.right != null) {
                        next.right.p = next.p;
                    }
                    next.left = this.left;
                    next.right = this.right;
                    next.p = this.p;
                    if(next.left != null) {
                        next.left.p = next;
                    }
                    if(next.right != null) {
                        next.right.p = next;
                    }
                    return next;
                }
            }
        }
    }

    public BSTNode find(String key) {
        if(key.compareTo(this.key) < 0) {
            if(this.left != null) {
                return this.left.find(key);
            } else {
                return null;
            }
        } else if(key.compareTo(this.key) > 0) {
            if(this.right != null) {
                return this.right.find(key);
            } else {
                return null;
            }
        } else {
            return this;
        }
    }

    public String min() {
        if(this.left != null) {
            return this.left.min();
        } else {
            return this.key;
        }
    }

    public BSTNode minNode() {
        if(this.left != null) {
            return this.left.minNode();
        } else {
            return this;
        }
    }

    public String max() {
        if(this.right != null) {
            return this.right.max();
        } else {
            return this.key;
        }
    }

    public String successor(String key) {
        BSTNode keyNode = find(key);
        if(keyNode == null) {
            return null;
        }
        BSTNode successorNode = keyNode.right;
        if(successorNode != null) {
            while(successorNode.left != null) {
                successorNode = successorNode.left;
            }
        } else {
            successorNode = keyNode;
            if(successorNode.p == null) {
                return null;
            }
            while(successorNode.p.left != successorNode) {
                successorNode = successorNode.p;
                if(successorNode.p == null) {
                    return null;
                }
            }
        }
        return successorNode.key;
    }
    
    public BSTNode successor(BSTNode node) {
        if(node == null) {
            return null;
        }
        BSTNode successorNode = node.right;
        if(successorNode != null) {
            while(successorNode.left != null) {
                successorNode = successorNode.left;
            }
        } else {
            successorNode = node;
            if(successorNode.p == null) {
                return null;
            }
            while(successorNode.p.left != successorNode) {
                successorNode = successorNode.p;
                if(successorNode.p == null) {
                    return null;
                }
            }
            return successorNode.p;
        }
        return successorNode;
    }

    public String inorder(String s) {
        if(this.left != null) {
            s = this.left.inorder(s);
        }
        s = s + this.key + " ";
        if(this.right != null) {
            s = this.right.inorder(s);
        }
        return s;
    }

    public int size() {
        int left = 0;
        int right = 0;

        if(this.left != null) {
            left = this.left.size();
        }
        if(this.right != null) {
            right = this.right.size();
        }
        return left + right + 1;
    }
}