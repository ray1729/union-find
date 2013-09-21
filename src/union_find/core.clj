(ns union-find.core
  (:require [union-find.weighted-quick-union :refer [weighted-quick-union union connected?]]))

(def cities ["London" "Birmingham" "Sheffield" "Bristol" "Leeds" "Liverpool" "Manchester"])

(def link-costs 
  [
   ["London" "Birmingham" 103]
   ["London" "Sheffield"  167]
   ["London" "Leeds" 175]
   ["London" "Bristol" 100]
   ["London" "Liverpool" 178]
   ["London" "Manchester" 181]

   ["Birmingham" "Sheffield"  91]
   ["Birmingham" "Leeds" 92 ]
   ["Birmingham" "Bristol" 79 ]
   ["Birmingham" "Liverpool" 75 ]
   ["Birmingham" "Manchester" 95]

   ["Sheffield" "Bristol" 180]
   ["Sheffield" "Leeds" 33]
   ["Sheffield" "Liverpool" 63]
   ["Sheffield" "Manchester" 37]

   ["Bristol" "Leeds" 171]
   ["Bristol" "Liverpool" 136]
   ["Bristol" "Manchester" 139]

   ["Leeds" "Liverpool" 73]
   ["Leeds" "Manchester" 40]

   ["Liverpool" "Manchester" 27]])

(def links (sort-by last(map (fn [[from to cost]] [(.indexOf cities from) (.indexOf cities to) cost]) link-costs)))

(def city-uf (weighted-quick-union (count cities)))

(defn add-link [[uf links] [from to cost]]
  (if (connected? uf from to)
    [uf links]
    [(union uf from to) (conj links [from to cost])]))

;; By the Power of Kruskal and by that of Union-Find:

(reduce + (map last (second (reduce add-link [city-uf []] links)))) ;-> 351
(reduce + (map last (second (reduce add-link [city-uf []] (reverse links))))) ;-> 988

(defn benchmark
  [N]
  (time (reduce add-link [(range N) []] (repeatedly N (fn [] [(rand-int N) (rand-int N) (rand-int N)]))))
  nil)
