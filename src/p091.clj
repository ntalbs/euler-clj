(ns p091)

(def LIMIT 50)

(def points
  (for [x (range (inc LIMIT)) y (range (inc LIMIT)) :when (not (= 0 x y))]
    [x y]))

(defn e [[x y]] (if (= y 0) nil (/ x y)))

(def triangles
  (for [p1 points p2 points
        :when (not= p1 p2)          ; exclude when p1 and p2 are the same point
        :when (not= (e p1) (e p2))] ; exclude when p1 and p2 are on the straight line
    [p1 p2]))

(defn inner-product [[a1 a2] [b1 b2]]
  (+ (* a1 b1) (* a2 b2)))

(defn subtract [[a1 a2] [b1 b2]]
  [(- a1 b1) (- a2 b2)])

(def right-triangles
  (for [[P Q] triangles
        :when (or (= 0 (inner-product P Q))
                  (= 0 (inner-product P (subtract Q P)))
                  (= 0 (inner-product Q (subtract Q P))))]
    [A B]))

; P, Q가 위치만 바뀐 경우가 있으므로 2로 나눠야 함
(defn solve []
  (/ (count right-triangles) 2))
