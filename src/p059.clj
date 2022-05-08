(ns p059
  (:require [util :refer [parse-int]]
            [clojure.string :refer [split trim]]))

(def encrypted-message
  (->> (split (slurp "data/p059_cipher.txt") #",")
       (map trim)
       (map parse-int)))

(defn decipher [encrypted key]
  (let [key (mapcat identity (repeat key))]
    (->> (map bit-xor encrypted key)
         (map char)
         (apply str))))

(defn solve []
  (let [a2z (range (int \a) (inc (int \z)))]
    (->> (for [a a2z b a2z c a2z] [a b c])
         (map #(decipher encrypted-message %))
         (filter #(re-matches #"[^`~\p{Cntrl}]+" %))
         (first)
         (map int)
         (apply +))))
