;; # Wolfram-Alpha API

^{:nextjournal.clerk/visibility :hide-ns}
(ns sicp.ch1.wa
  "Play with Wolframm-Alpha API"
   (:require 
    [cheshire.core :as json]
    [nextjournal.clerk :as clerk]))

(clerk/serve! {:watch-paths ["src"]})

(defn swap-args [f a b]
  (f b a))

(defn format-figure [[title url]]
  [:figure
   [:img {:src url}]
   [:figcaption title]])

(def extract-data #(->> %
              :subpods
              (keep :img)
              (map (juxt :title :src))
              first
              format-figure
              ))

(def response (->>
 (slurp "https://api.wolframalpha.com/v2/query?input=hamsters&format=plaintext,image&output=JSON&appid=Q34AAQ-3KT58PVKA4")
 (swap-args json/parse-string true)
 :queryresult
 ))

(->> response
 :pods
 (map extract-data)
 (into [:body ])
 clerk/html
)


