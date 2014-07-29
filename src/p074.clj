;; #074

(ns p074
  (:require [util :refer (digits factorial)]))

(defn sum-of-factorial [n]
  (->> (digits n)
       (map factorial)
       (apply +)))

(def sum-of-factorial (memoize sum-of-factorial))

(defn count-steps [n]
  (loop [s n chain #{n} cnt 1]
    (let [x (sum-of-factorial s)]
      (if (contains? chain x) cnt
          (recur x (conj chain x) (inc cnt))))))

(defn p074 []
  (->> (range 1 (inc 1000000))
       (filter #(= 60 (count-steps %)))
       count))

; Takes about 25 secs. Need to be improved.
(defn solve []
  (time (println (p074))))
