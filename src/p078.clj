;; http://en.wikipedia.org/wiki/Partition_(number_theory)#Partition_function_formulas
;; http://en.wikipedia.org/wiki/Pentagonal_number_theorem

(ns p078
  (:require [util :refer [infix]]))

(definline s [k] `(if (even? ~k) 1 -1))

(defn g [k] (infix ((k * ((3 * k) - 1)) / 2)))

(defn p [n]
  (cond (= n 0) 1
        (= n 1) 1
        (= n 2) 2
        (= n 3) 3
        :else (->> (interleave (iterate inc 1) (iterate dec -1))
                   (take-while #(<= 0 (- n (g %))))
                   (map (fn [k] (*' (s (dec k)) (p (-' n (g k))))))
                   (apply +'))))

(def p (memoize p))

(defn p078 []
  (->> (iterate inc 1)
       (map #(vector % (p %)))
       (drop-while #(not= 0 (mod (second %) 1000000)))
       first))

(defn solve []
  (time (p078)))
