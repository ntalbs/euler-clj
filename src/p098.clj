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

(defn c->n [w]
  (->> (for [n (sqnums (count w))] (into {} (map vector w (digits n))))
       (filter (fn [m]
                 (= 1 (->> (group-by second m)
                           (map (fn [[k v]] (count v)))
                           (apply max)))))))

(defn w->sqns [w]
  (letfn [(same-letters-have-same-digit? [m]
            (= 1 (->> (group-by first m)
                      (map (fn [[_ v]] (count (distinct v))))
                      (apply max))))
          (diff-letters-have-diff-digits? [m]
            (= 1 (->> (group-by second m)
                      (map (fn [[_ v]] (count v)))
                      (apply max))))]
    (->> (for [n (sqnums (count w))] (map vector w (digits n)))
         (filter same-letters-have-same-digit?)
         (filter diff-letters-have-diff-digits?)
         (map (fn [x] (into {} x))))))

(defn check [[w1 w2]]
  (let [sqns (sqnums (count w1))
        ms (w->sqns w1)
        ns (for [m ms] (digits-to-num (map #(m %) w2)))]
    (filter #(contains? sqns %) ns)))

(defn solve []
  (->> anagrams
       (map #(check %))
       (remove empty?)
       (flatten)
       (apply max)))
