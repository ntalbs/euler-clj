(ns util
  (:require [clojure.contrib.lazy-seqs :refer (primes)]))

(defn parse-int [s]
  (Integer/parseInt s))

(defn digits
  "Retruns the list of digits of n."
  [n]
  (loop [n n acc '()]
    (if (= n 0)
      acc
      (recur (quot n 10) (conj acc (int (rem n 10)))))))

(defn- add-to-first
  "Returns a new list with its first element is (+ x (first ls)). "
  [ls x]
  {:pre [(list? ls)]}
  (let [fv (first ls)]
    (if (nil? fv)
      nil
      (conj (rest ls) (+ fv x)))))

(defn- normailize-digits
  "Returns a normailize sequence of digits.
  Normailize here means that every digit in the sequence is single digit."
  [ds]
  (loop [ds (reverse ds), acc '()]
    (let [cur (first ds)
          rst (rest ds)
          d10 (quot cur 10)
          d1 (rem cur 10)]
      (if (empty? rst)
        (if (= d10 0)
          (conj acc d1)
          (recur (list d10) (conj acc d1)))
        (recur (add-to-first rst d10) (conj acc d1))))))

(defn- lpad
  "Returns new seqence with cnt of 0 padded on the left."
  [ds cnt]
  (concat (repeat cnt 0) ds))

(defn digits+
  "Returns sum of two digits."
  [ds1 ds2]
  (let [cnt1 (count ds1)
        cnt2 (count ds2)
        ds1 (if (< cnt1 cnt2) (lpad ds1 (- cnt2 cnt1)) ds1)
        ds2 (if (< cnt1 cnt2) ds2 (lpad ds2 (- cnt1 cnt2)))
        added  (map + ds1 ds2)]
    (normailize-digits added)))

(defn digits*
  "Returns product of given digits ds and n."
  [ds n]
  (normailize-digits (map #(* % n) ds)))

(defn gcd
  "Returns the greatest common divisor of a and b."
  [a b] (if (= b 0) a (recur b (rem a b))))

(defn lcm
  "Returns the least common multiplier of a and b."
  [a b] (/ (* a b) (gcd a b)))

(defn factorial
  "Returns the factorial of n."
  [n] (apply *' (range 1 (inc n))))

(defn perfect-square?
  "Returns true if n is perfect number which means sqrt(n) is intger."
  [n]
  (let [r (int (Math/sqrt n))]
    (= n (* r r))))

(defn pow
  "Compute x^n."
  [x n]
  (loop [n n acc 1]
    (if (<= n 0)
      acc
      (recur (dec n) (*' acc x)))))

(defn- ceil [x]
  (inc (int x)))

;; Primality test
;; https://en.wikipedia.org/wiki/Primality_test
(defn prime?
  "Returns true if n is prime."
  [n]
  (cond (or (= n 2) (= n 3)) true
        (or (<= n 1) (= 0 (mod n 2)) (= 0 (mod n 3))) false
        :else (loop [i 5]
                (cond (> (* i i) n) true
                      (or (= 0 (mod n i)) (= 0 (mod n (+ i 2)))) false
                      :else (recur (+ i 6))))))

(defn factorize
  "Returns a sequence of pairs of prime factor and its exponent."
  [n]
  (loop [n n, ps primes, acc []]
    (let [p (first ps)]
      (if (<= p n)
        (if (= 0 (mod n p))
          (recur (quot n p) ps (conj acc p))
          (recur n (rest ps) acc))
        (->> (group-by identity acc)
             (map (fn [[k v]] [k (count v)])))))))

(defn divisor?
  "Returns true if x is a divisor of n."
  [x n] (zero? (rem n x)))

(defn proper-divisors
  "Returns the proper divisors of n."
  [n]
  (let [bound (ceil (Math/sqrt n))
        uniq #(if (= %1 %2) [%1] [%1 %2])]
    (->> (range 1 bound)
         (mapcat #(if (divisor? % n) (uniq % (quot n %))))
         (remove #(or (nil? %) (= n %)))
         sort)))

(defn aliquot-sum1
  "Returns the sum of n's proper divisors."
  [n]
  (apply + (proper-divisors n)))

(defn aliquot-sum2 [n]
  (if (<= n 1) 0
      (->> (factorize n)
           (map (fn [[p a]] (reduce + (for [i (range (inc a))] (pow p i)))))
           (reduce *)
           (#(- % n)))))

(defn aliquot-sum3 [n]
  (if (<= n 1) 0
      (->> (factorize n)
           (map (fn [[p a]] (/ (dec (pow p (inc a))) (dec p))))
           (reduce *)
           (#(- % n)))))

(defn- valid?
  "returns true if ops is valid sequence of expression. used internally in calc macro. "
  [ops]
  (and (odd? (count ops))
       (->> ops
            rest
            (take-nth 2)
            (apply =))))

(defmacro split [re s]
  `(clojure.string/split ~s ~re))

(defmacro infix
  "transform then given infix arithematic expresssion to prefix and compute it."
  [ops]
  (if (coll? ops)
    (if (valid? ops)
      (cons (second ops) (for [x (take-nth 2 ops)] `(infix ~x)))
      (throw (Exception. "Invalid expression.")))
    ops))
