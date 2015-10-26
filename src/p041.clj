(ns p041
  (:require [util :refer [parse-int prime?]]
            [clojure.contrib.combinatorics :refer [permutations]]))

(defn solve []
  (->> (reverse (reductions conj [1] (range 2 10)))
       (map reverse)
       (mapcat permutations)
       (map #(parse-int (apply str %)))
       (filter prime?)
       first))
