;; #062
;; Find the smallest cube for which exactly five permutations of its digits are cube.

(use '[clojure.contrib.combinatorics :only (permutations)]
     '[util :only (digits)])

(def cubics
  (->> (iterate inc 1)
       (map #(* % % %))))

(defn sorted-digits [n]
  (sort (digits n)))

(defn p062 [cnt-limit]
  (->> (take cnt-limit cubics)
       (group-by #(sorted-digits %))
       (filter (fn [[k v]] (<= 5 (count v))))
       (mapcat (fn [[k v]] v))
       (apply min)))

; check for first 10,000 cubics
(time (println (p062 10000)))

