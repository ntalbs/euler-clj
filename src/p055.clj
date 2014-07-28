;; #055
;; How many Lychrel numbers are there below ten-thousand?

(ns p055)

(defn reverse-num [n]
  (bigint (clojure.string/reverse (str n))))

(defn palindrome? [n]
  (= n (reverse-num n)))

(defn lychrel? [n]
  (loop [n n cnt 1]
    (if (< 50 cnt)
      true
      (let [sn (+' n (reverse-num n))]
        (if (palindrome? sn)
          false
          (recur sn (inc cnt)))))))

(defn p055 []
  (->> (range 10 (inc 10000))
       (filter lychrel?)
       count))

(defn solve []
  (time (println (p055))))
