(ns union-find.quick-find)

(defn quick-find
  [n]
  (vec (range n)))

(defn connected?
  [qf p q]
  (= (qf p) (qf q)))

(defn union
  [qf p q]
  (let [pid (qf p)
        qid (qf q)]
    (mapv (fn [e] (if (= e pid) qid e)) qf)))
