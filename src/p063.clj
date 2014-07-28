;; #063
;; How many n-digit positive integers exist which are also an nth power?

(ns p063)

(defn power [x n] (apply *' (repeat n x)))
(defn count-digit [x] (inc (int (Math/log10 x))))

(defn n-digits-and-nth-power
  "Returns the sequence of numbers that is n-digit and n-th power simultaneously."
  [n]
  (->> (iterate inc 1)
       (map (fn [x] (power x n)))
       (drop-while (fn [x] (< (count-digit x) n)))
       (take-while (fn [x] (= (count-digit x) n)))))

(def limit
  "Max of n, where n-digit and n-power number is possible.
   This value can be determined by checking where the number of digits of 9^n become less than n."
  (->> (iterate inc 1)
       (map #(vector % (count-digit (power 9 %))))
       (take-while (fn [[n d]] (= n d)))
       count))

(defn p063 []
  (->> (range 1 (inc limit))
       (mapcat n-digits-and-nth-power)
       count))

(defn solve []
  (time (println (p063))))
