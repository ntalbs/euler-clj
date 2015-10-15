(ns p036
  (:require [clojure.string :as s]))

(defn to-binary-str [n]
  {:pre [(<= 0 n)]}
  (loop [n n acc '()]
    (if (< 0 n)
      (recur (quot n 2) (conj acc (rem n 2)))
      (if (empty? acc)
        "0"
        (apply str acc)))))

(defn- palindromic? [s]
  (= s (s/reverse s)))

(defn- dec-palindromic? [n]
  (palindromic? (str n)))

(defn- bin-palindromic? [n]
;;  (palindromic? (to-binary-str n)))
  (palindromic? (Integer/toBinaryString n)))

(defn solve []
  (->> (range 1 (inc 1000000))
       (filter (fn [n] (and (dec-palindromic? n) (bin-palindromic? n))))
       (apply +)))
