;; euler 102
;; 원점에서 삼각형의 각 점을 향하는 벡터를 A, B, C라 하면,
;; 원점이 삼각형 안에 포함되는 경우는 A X B, B X C, C X A가 모두 같은 방향이다.
;; 원점이 삼각형 밖에 있는 경우는 위 벡터곱의 결과 중 하나가 반대 방향이 된다.

(ns p102
  (:require [util :refer [parse-int]]
            [clojure.string :refer [split]]))

(defn cross-product [[a1 a2] [b1 b2]]
  (- (* a1 b2) (* a2 b1)))

(defn dirs [[a1 a2] [b1 b2] [c1 c2]]
  [(cross-product [a1 a2] [b1 b2])
   (cross-product [b1 b2] [c1 c2])
   (cross-product [c1 c2] [a1 a2])])

(defn check [[a1 a2 b1 b2 c1 c2]]
  (let [d (dirs [a1 a2] [b1 b2] [c1 c2])]
    (or (every? pos? d) (every? neg? d))))

(def triangles
  (map (fn [s] (map parse-int (split s #",")))
       (split (slurp "data/triangles.txt") #"\n")))

(defn p102 []
  (count (filter #(= % true) (map check triangles))))

(defn solve []
  (time (println (p102))))
