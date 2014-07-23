;; #017
;; If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
;; how many letters would be used?

(ns p017)

(def one2nineteen
  ["" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine" "ten"
   "eleven" "twelve" "thirteen" "fourteen" "fifteen"
   "sixteen" "seventeen" "eighteen" "nineteen"])
(def deca
  ["" "ten" "twenty" "thirty" "forty" "fifty" "sixty" "seventy" "eighty" "ninety"])

(defn s-under20 [n]
  (one2nineteen n))

(defn s-under100 [n]
  (let [d1 (deca (quot n 10))
        d2 (s-under20 (mod n 10))]
    (format "%s %s" d1 d2)))

(defn s-under1000 [n]
  (let [last-two-digits (mod n 100)
        d1 (s-under20 (quot n 100))]
    (cond (= 0 last-two-digits) (format "%s hundred" d1)
          (< last-two-digits 20) (format "%s hundred and %s" d1 (s-under20 last-two-digits))
          :else (format "%s hundred and %s %s"
                        d1
                        (deca (quot (mod n 100) 10))
                        (s-under20 (mod n 10))))))

(defn num2str
  "Returns a string representention of number n. n should be in the range 1 to 1000."
  [n]
  (cond (< n 20) (s-under20 n)
        (< n 100) (s-under100 n)
        (< n 1000) (s-under1000 n)
        (= n 1000) "one thousand"
        :else nil))

(defn p017 []
  (->> (range 1 (inc 1000))
       (map num2str)
       (apply str)
       (filter #(not= % \space))
       count))

(defn solve []
  (time (println (p017))))
