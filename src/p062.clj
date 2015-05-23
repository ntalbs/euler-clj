;; Find the smallest cube for which exactly five permutations of its digits are cube.

(ns p062
  (:require [clojure.contrib.combinatorics :refer (permutations)]
            [util :refer (digits)]))

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
(defn solve []
  (time (println (p062 10000))))
