(ns p079
  (:require [clojure.set :as set]
            [util :refer (parse-int digits)]))

(def keylog
  (-> (slurp "data/keylog.txt")
      (clojure.string/split #"\r\n")
      distinct
      sort))

(def pairs
  (->> (map #(parse-int %) keylog)
       (map digits)
       (mapcat #(partition 2 1 %))
       distinct))

(defn minus [s1 s2]
  (set/difference s1 s2))

(defn find-key [ps acc]
  (let [ps (filter #(not= (last acc) (first %)) ps)]
    (if (= 1 (count ps))
      (into acc (first ps))
      (recur ps
             (into acc (minus (set (map first ps)) (set (map second ps))))))))

(defn solve []
  (apply str (find-key pairs [])))
