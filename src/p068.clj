(ns p068
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn p068 []
  (->> (permutations [1 2 3 4 5 6 7 8 9 10])
       (filter (fn [[a b c d e f g h i j]]
                 (= (+ a b c)
                    (+ j f b)
                    (+ i e f)
                    (+ h d e)
                    (+ g c d))))
       (filter (fn [[a b c d e f g h i j]]
                 (= a (min a g h i j))))
       (map (fn [[a b c d e f g h i j]] (apply str [a b c g c d h d e i e f j f b])))
       (filter #(= 16 (count %)))
       (map #(Long/parseLong %))
       (apply max)))

(defn solve []
  (time (p068)))
