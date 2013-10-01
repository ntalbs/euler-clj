;; #089

(require '[clojure.string :as str])

(def romans (str/split (slurp "data/roman.txt") #"\r\n"))

(defn shorten [r]
  (-> r
      (str/replace "DCCCC" "CM")
      (str/replace "CCCC"  "CD")
      (str/replace "LXXXX" "XC")
      (str/replace "XXXX"  "XL")
      (str/replace "VIIII" "IX")
      (str/replace "IIII"  "IV")))

(defn count-chars [coll]
  (->> (map count coll)
       (apply +)))

(defn p089 []
  (let [cnt-before (count-chars romans)
        cnt-after (count-chars (map shorten romans))]
    (- cnt-before cnt-after)))

(time (println (p089)))
