;; #022
;; What is the total of all the name scores in the file (names.txt)?

(require '[clojure.string :only (replace split) :as str])

(def names
  (-> (slurp "data/names.txt")
      (str/replace "\"" "")
      (str/split #",")
      sort))

(defn score [name]
  (apply + (map (fn [a] (- (int a) (dec (int \A)))) name)))

(defn p022 []
  (->> (map-indexed vector names)
       (map (fn [[i e]] (* (inc i) (score e))))
       (apply +)))

(time (println (p022)))
