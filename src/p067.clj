(ns p067
  (:require [util :refer [parse-int]]
            [clojure.string :refer [split]]))

(defn- split-by-line [content]
  (split content #"\r\n"))

(defn- parse-line [line]
  (->> (split line #" ") (map parse-int)))

(def triangle
  (->> (slurp "data/triangle.txt")
       split-by-line
       (map parse-line)
       reverse))

(defn find-max [t]
  (first (reduce (fn [ls us] (map max (map + ls us) (map + (rest ls) us))) t)))

(defn solve []
  (find-max triangle))
