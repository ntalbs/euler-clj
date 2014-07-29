(ns p092
  (:require [util :refer [digits]]))

(defn sum-sq-digits [n]
  (->> (digits n)
       (map #(* % %))
       (reduce +)))

(defn reach89? [n]
  (cond (= n 1) false
        (= n 89) true
        :else (recur (sum-sq-digits n))))

(defn p092 []
  (->> (range 1 10000000)
       (filter reach89?)
       (count)))

(defn solve []
  (time (println (p092))))
