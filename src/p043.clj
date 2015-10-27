(ns p043
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn- svn
  "subvector number"
  [ds start-pos]
  (->> (subvec ds start-pos (+ start-pos 3))
       (apply str)
       Integer/parseInt))

(defn solve []
  (->> (permutations (range 10))
       (drop-while #(= 0 (first %)))
       (filter #(= 0 (rem (svn % 7) 17)))
       (filter #(= 0 (rem (svn % 6) 13)))
       (filter #(= 0 (rem (svn % 5) 11)))
       (filter #(= 0 (rem (svn % 4) 7)))
       (filter #(= 0 (rem (svn % 3) 5)))
       (filter #(= 0 (rem (svn % 2) 3)))
       (filter #(= 0 (rem (svn % 1) 2)))
       (map #(apply str %))
       (map #(Long/parseLong %))
       (reduce +)))
