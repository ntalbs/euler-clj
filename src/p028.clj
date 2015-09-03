(ns p028)

(def size 1001)

(defn- square [n] (* n n))

;; d(n,k) = 2n(k+1) + (2n-1)^2
(defn- d
  "n번째 ring의 대각선 요소 수를 구하는 함수. n=1,2,3..., k=(0,1,2,3)"
  [n k]
  (+ (* 2 n (inc k)) (square (dec (* 2 n)))))

(defn solve []
  (->> (for [n (range 1 (inc (int (/ size 2)))) k (range 4)] (d n k))
       (apply +)
       (+ 1)))
