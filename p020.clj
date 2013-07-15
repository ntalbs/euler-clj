;; #020
;; sum of the digits in the number 100!

(use '[util :only [digits factorial]])

(defn p020 []
  (->> (factorial 100)
       digits
       (apply +)))

(time (println (p020)))
