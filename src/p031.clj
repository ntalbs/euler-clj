;; SICP s1.2.2, p50 (Korean Ed.)

(ns p031)

(defn- value [n]
  (case n
    1 1
    2 2
    3 5
    4 10
    5 20
    6 50
    7 100
    8 200))

(defn- cc [amt n]
  (cond
    (= amt 0) 1
    (< amt 0) 0
    (= n 0) 0
    :else (+ (cc amt (dec n))
             (cc (- amt (value n)) n))))

(defn solve []
  (cc 200 8))
