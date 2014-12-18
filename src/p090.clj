(ns p090
  (:require [clojure.math.combinatorics :refer [combinations]]
            [clojure.set :refer [difference]]
            [util :refer [parse-int]]))

(def sqnums (set (map #(* % %) (range 1 10))))

(def digits-sets
  (->> (combinations (range 10) 6)
       (map set)))

(def set-pairs
  (set (map set (combinations digits-sets 2))))

(defn adjust-ds [ds]
  (cond (contains? ds 6) (conj ds 9)
        (contains? ds 9) (conj ds 6)
        :else ds))

(defn check [ds1 ds2]
  (->> (for [d1 (adjust-ds ds1), d2 (adjust-ds ds2)] [(+ (* d1 10) d2) (+ (* d2 10) d1)])
       flatten
       set
       (difference sqnums)
       empty?))

(defn p090 []
  (->> set-pairs
       (filter (fn [pair] (check (first pair) (second pair))))
       count))

(defn solve []
  (time (println (p090))))
