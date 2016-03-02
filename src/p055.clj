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

(defn solve []
  (->> (range 10 (inc 10000))
       (filter lychrel?)
       count))
