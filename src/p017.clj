(ns p017)

(def one2nineteen
  ["" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine" "ten"
   "eleven" "twelve" "thirteen" "fourteen" "fifteen"
   "sixteen" "seventeen" "eighteen" "nineteen"])

(def deca
  ["" "ten" "twenty" "thirty" "forty" "fifty" "sixty" "seventy" "eighty" "ninety"])

(defn s-under20 [n]
  {:pre [(<= 0 n)]}
  (one2nineteen n))

(defn s-under100 [n]
  (if (< n 20)
    (s-under20 n)
    (let [d1 (deca (quot n 10))
          d2 (s-under20 (mod n 10))]
      (format "%s %s" d1 d2))))

(defn s-under1000 [n]
  (if (< n 100)
    (s-under100 n)
    (let [d1 (s-under20 (quot n 100))
          last-two-digits (s-under100 (mod n 100))]
      (if (empty? last-two-digits)
        (format "%s hundred" d1)
        (format "%s hundred and %s" d1 last-two-digits)))))

(defn num2str [n]
  (cond (< n 1000) (s-under1000 n)
        (= n 1000) "one thousand"
        :else nil))

(defn solve []
  (->> (range 1 (inc 1000))
       (map num2str)
       (apply str)
       (filter #(not= % \space))
       count))
