(ns p098
  (:require [util :refer [digits]]))

(def words
  (clojure.string/split (slurp "data/words.txt") #"\"(,\")?"))

(def anagrams
  (->> words
       (group-by (fn [w] (sort w)))
       (filter (fn [[k ws]] (<= 2 (count ws))))
       (map second)))

(def sqnums
  (->> (iterate inc 1)
       (map #(* % %))
       (take-while #(< (Math/log10 %) 9))
       (group-by #(inc (int (Math/log10 %))))
       (map (fn [[k vs]] [k (set vs)]))
       (into {})))

(defn digits-to-num [ds]
  (loop [ds (reverse ds) p 1 n 0]
    (if (seq? ds)
      (recur (next ds) (* p 10) (+ n (* (first ds) p)))
      n)))

(defn c->n [w sqns]
  (->> (for [n sqns] (into {} (map vector w (digits n))))
       (filter (fn [m]
                 (= 1 (->> (group-by second m)
                           (map (fn [[k v]] (count v)))
                           (apply max)))))))

(defn check [[w1 w2]]
  (let [sqns (sqnums (count w1))
        ms (c->n w1 sqns)
        ns (for [m ms] (digits-to-num (map #(m %) w2)))]
    (first (filter #(contains? sqns %) ns))))

(defn p098 []
  (->> anagrams
       (map #(check %))
       (remove nil?)
       (apply max)))

(defn solve []
  (time (println (p098))))
