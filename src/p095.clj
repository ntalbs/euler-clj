;; slow. need to be improved.

(ns p095)

(def limit 1000000)

(def n->sod
  (->> (for [i (range 1 (/ limit 2))
             j (range (* 2 i) limit i)]
         [j i])
       (group-by first)
       (map (fn [[k v]] [k (map second v)]))
       (map (fn [[k v]] [k (apply + v)]))
       (into {})))

(defn find-chain [n]
  (loop [n n s #{} acc []]
    (if-not (contains? s n)
      (recur (n->sod n) (conj s n) (conj acc n))
      (if (or (nil? (last acc)) (not= n (first acc))) nil acc))))

(defn longest-chain [limit]
  (->> (range 2 (inc limit))
       (map find-chain)
       (remove nil?)
       (apply max-key count)))

(defn solve []
  (apply min (longest-chain limit)))
