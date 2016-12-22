(ns p081
  (:require [util :refer [split parse-int]]))

(def ^:private m
  (->> (slurp "data/matrix.txt")
       (split  #"\r\n")
       (mapv (fn [line] (mapv parse-int (split #"," line ))))))

(defn- t
  "returns the lists of positions of elemements in diagonal order
   (/ direction) from right-bottom to left-top."
  [n]
  (concat (for [i (range n)]
            (for [j (range (inc i))]
              [(- n j 1) (+ (- n i 1) j)]))
          (for [i (range (dec n))]
            (for [j (range (- (dec n) i))]
              [(- (dec n) j i 1) j]))))

(defn- mg [xs ys]
  (flatten (vector (first xs)
                   (map min (rest xs) ys)
                   (last ys))))

(defn- min-sum [xs ys]
  (if (< (count xs) (count ys))
    (mg (map + xs ys) (map + xs (rest ys)))
    (map min (map + xs ys) (map + (rest xs) ys))))

(defn solve []
  (->> (t (count m))
       (map (fn [ks] (for [k ks] (get-in m k))))
       (reduce min-sum)
       first))
