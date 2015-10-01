;; SICP s1.2.2, p50 (Korean Ed.)

(ns p031)

(defn- value [coin]
  (case coin
    1 1
    2 2
    3 5
    4 10
    5 20
    6 50
    7 100
    8 200))

(defn- count-change [amt]
  (letfn [(cc [amt coin]
            (cond
              (= amt 0) 1
              (< amt 0) 0
              (= coin 0) 0
              :else (+ (cc amt (dec coin))
                       (cc (- amt (value coin)) coin))))]
    (cc amt 8)))

(defn solve []
  (count-change 200))
