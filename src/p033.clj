;; #033

(ns p033)

(defn decomp-num [n] {:pre [(<= 10 n 99)]}
  [(quot n 10) (rem n 10)])

(defn st-cancel "strange cancel" [[n d]]
  (let [[n-d1 n-d2] (decomp-num n) [d-d1 d-d2] (decomp-num d)]
    (cond (= n-d1 d-d1) (/ n-d2 d-d2)
          (= n-d1 d-d2) (/ n-d2 d-d1)
          (= n-d2 d-d1) (/ n-d1 d-d2)
          (= n-d2 d-d2) (/ n-d1 d-d1)
          :else nil)))

(def rs
  (for [n (range 10 100) d (range 10 100)
        :when (< n d)
        :when (not= 0 (mod n 10))
        :when (not= 0 (mod d 10))]
    [n d]))

(defn p033 []
  (reduce *
          (map (fn [[n d]] (/ n d))
               (filter (fn [[n d]] (= (/ n d) (st-cancel [n d]))) rs))))

(defn solve []
  (time (println (p033))))
