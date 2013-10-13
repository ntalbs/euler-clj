;; #059

(use '[util :only [parse-int]]
     '[clojure.string :only [split trim]])

(def encrypted-message
  (->> (split (slurp "data/cipher1.txt") #",")
       (map trim)
       (map parse-int)))

(defn decipher [encrypted key]
  (let [key (mapcat (fn [v] v) (repeat (map int key)))]
    (->> (map bit-xor encrypted key)
         (map char)
         (apply str))))

(def a2z
  (map char (range (int \a) (inc (int \z)))))

(def passwd-candidates
  (for [a a2z b a2z c a2z]
    (str a b c)))

(defn p059 []
  (->> (map #(decipher encrypted-message %) passwd-candidates)
       (filter #(re-matches #"[^`~\p{Cntrl}]+" %))
       (first)
       (map int)
       (apply +)))

(time (println (p059)))
