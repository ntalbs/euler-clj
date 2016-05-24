(ns p068
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn solve []
  (->> (permutations [1 2 3 4 5 6 7 8 9 10])
       (filter (fn [[a b c d e f g h i j]]
                 (= (+ a b c)
                    (+ d c e)
                    (+ f e g)
                    (+ h g i)
                    (+ j i b))))
       (filter (fn [[a b c d e f g h i j]] (= a (min a d f h j))))
       (map (fn [[a b c d e f g h i j]] (apply str [a b c d c e f e g h g i j i b])))
       (filter #(= 16 (count %)))
       (map #(Long/parseLong %))
       (apply max)))
