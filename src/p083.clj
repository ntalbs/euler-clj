(ns p083
  (:require [util :refer [split parse-int]]
            [clojure.data.priority-map]))

(def ^:private m
  (->> (slurp "data/matrix.txt")
       (split  #"\r\n")
       ;; (mapv (fn [line] (mapv parse-int (split #"," line ))))))
       (mapv (fn [line] (mapv (fn [s] {:val (parse-int s)}) (split #"," line ))))))

(def ^:private SIZE (count m))

(def ^:private weight 5)

(def ^:private start-node
  (let [v (get-in m [0 0 :val])]
    {:row 0, :col 0, :val v :sum v}))

(defn- manhattan [n]
  (- (* 2 SIZE) (n :row) (n :col)))

(defn- destination? [n]
  (and (= (:row n) (:col n) (dec SIZE))))

(defn- node-comparator [n1 n2]
  (- (+ (n1 :sum) (* weight (manhattan n1))) (+ (n2 :sum) (* weight (manhattan n2)))))

(defn- neighbor? [n1 n2]
  (cond
    (nil? n1) false
    (nil? n2) false
    :else (let [dr (Math/abs (- (n1 :row) (n2 :row)))
                dc (Math/abs (- (n1 :col) (n2 :col)))]
            (= 1 (+ dr dc)))))

(defn- neighbors [current]
  (let [prev1  (current :prev)
        prev2  (if prev1 (prev1 :prev))
        deltas [[-1 0] [0 1] [1 0] [0 -1]]]
    (->> deltas
         (map (fn [[dr dc]] [(+ (current :row) dr) (+ (current :col) dc)]))
         (remove (fn [[r c]] (or (< r 0) (>= r SIZE) (< c 0) (>= c SIZE))))
         (map (fn [[r c]]
                (let [v (get-in m [r c :val])]
                  {:row r, :col c, :prev current, :val v, :sum (+ (current :sum) v)})))
         (remove #(= % prev1))
         (remove #(neighbor? % prev2)))))

(defn- path [to-node]
  (loop [n to-node acc '()]
    (if (nil? (n :prev))
      (conj acc [(n :row) (n :col)])
      (recur (n :prev) (conj acc [(n :row) (n :col)])))))

(defn solve []
  (loop [data    m
         pq      (sorted-set-by node-comparator start-node)
         current (first pq)]
    (if (destination? current)
      (do
        (println (path current))
        (current :sum))
      (let [prev1 (current :prev)
            prev2 (if prev1 (prev1 :prev))
            ns    (->> (neighbors current)
                       (filter (fn [{r :row c :col s :sum}]
                                 (let [prev-sum (get-in data [r c :sum])]
                                   (or (nil? prev-sum) (< s prev-sum))))))
            pq    (apply conj (disj pq current) ns)]
        (recur (reduce (fn [m {r :row c :col s :sum}] (update-in m [r c] assoc :sum s)) data ns)
               pq
               (first pq))))))
