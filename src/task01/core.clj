(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn get-links []
  (let [data (parse "clojure_google.html")]
    (loop [info (flatten data), flag false, result []]
      (if (empty? info)
        result
        (do (cond (= (:class (second info)) "r")
            (recur (rest info) true result)
            (and (= :a (first info)) flag)
            (recur (rest info) false (conj result (:href (second info)))) :default (recur (rest info) flag result)))))))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))
