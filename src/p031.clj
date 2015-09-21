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

(defn cc [amount kinds-of-coins]
  (cond (= amount 0) 1
        (or (< amount 0) (= kinds-of-coins 0)) 0
        :else (+ (cc amount (dec kinds-of-coins))
                 (cc (- amount (first-denomination kinds-of-coins)) kinds-of-coins))))

(defn count-change [amount]
  (cc amount 8))

(defn solve []
  (count-change 200))
