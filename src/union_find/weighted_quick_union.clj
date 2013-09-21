(ns union-find.weighted-quick-union)

(defn weighted-quick-union
  [n]
  {:parent (vec (range n))
   :size   (vec (repeat n 1))})

(defn root
  [qu p]
  (let [parent (get-in qu [:parent p])]
    (if (= parent p)
      parent
      (recur qu parent))))

(defn connected?
  [qu p q]
  (= (root qu p) (root qu q)))

(defn union
  [qu p q]
  (let [p-root (root qu p)
        q-root (root qu q)
        new-size (+ (get-in qu [:size p-root])
                    (get-in qu [:size q-root]))]
    (if (< (get-in qu [:size p-root])
           (get-in qu [:size q-root]))
      (-> qu
          (assoc-in [:parent p-root] q-root)
          (assoc-in [:size q-root] new-size))
      (-> qu
          (assoc-in [:parent q-root] p-root)
          (assoc-in [:size p-root] new-size)))))
