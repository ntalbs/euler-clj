(ns p099
  (:require [util :refer [parse-int]]
            [clojure.string :refer [split]]))

(def log-vals
  (->> (split (slurp "data/base_exp.txt") #"\r\n")
       (map (fn [s] (split s #",")))
       (map (fn [[b e]] [(parse-int b) (parse-int e)]))
       (map (fn [[b e]] (* e (Math/log10 b))))
       (map-indexed vector)))

(defn solve []
  (+ 1 (first (apply max-key (fn [[i v]] v) log-vals)))) ; +1, since log-vals index is zero-based
