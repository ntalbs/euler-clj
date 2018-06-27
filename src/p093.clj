(ns p093
  (:require [clojure.math.combinatorics :refer [permutations combinations]]))

(defn- div [a b]
  (if (zero? b) -1 (/ a b)))

(defn- target [[a b c d]]
  (let [ops [+ - * div]]
    (flatten
     (for [op1 ops, op2 ops, op3 ops]
       [(op3 (op2 (op1 a b) c) d)       ; ((a op1 b) op2 c) op3 d
        (op2 (op1 a b) (op3 c d))       ; (a op1 b) op2 (c op3 d)
        (op3 (op1 a (op2 b c)) d)       ; (a op1 (b op2 c)) op3 d
        (op1 a (op3 (op2 b c) d))       ; a op1 ((b op2 c) op3 d)
        (op1 a (op2 b (op3 c d)))]))))  ; a op1 (b op2 (c op3 d))

(defn- gen [[a b c d]]
  (->> (permutations [a b c d])
       (mapcat #(target %))
       (distinct)
       (filter #(and (pos? %) (integer? %)))
       (sort)
       (hash-map :set [a b c d] :target)))

(defn- count-consective [ns]
  (->> (partition 2 1 ns)
       (take-while (fn [[a b]] (= a (dec b))))
       (count)
       (inc)))

(defn- digits->int [ds]
  (reduce (fn [acc x] (+ (* 10 acc) x)) ds))

(defn solve []
  (->> (combinations (range 1 10) 4)
       (map gen)
       (apply max-key (fn [x] (count-consective (:target x))))
       (:set)
       digits->int))
