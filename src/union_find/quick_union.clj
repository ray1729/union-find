(ns union-find.quick-union)

(defn quick-union
  [n]
  (vec (range n)))

(defn root
  [qf p]
  (let [parent (qf p)]
    (if (= parent p)
      parent
      (recur qf parent))))

(defn connected?
  [qf p q]
  (= (root qf p) (root qf q)))

(defn union
  [qf p q]
  (let [p-root (root qf p)
        q-root (root qf q)]
    (assoc qf p-root q-root)))
