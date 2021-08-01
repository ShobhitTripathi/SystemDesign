# SystemDesign
Algorithms useful for System Designs.

APIRateLimiter :
API Rate limiting system that monitors the number of requests per a window time(i.e. could be second/hour etc) a service agrees to allow. 
IF the number of requests exceeds the allowed limit the rate limiter should block all excess calls.

TypeaheadSuggestions : 
enable users to search for known and frequently searched terms. As the user types into the search box, 
it tries to predict the query based on the characters the user has entered and gives a list of suggestions to complete the query. 
Typeahead suggestions help the user to articulate their search queries better. 
It’s not about speeding up the search process but rather about guiding the users and lending them a helping hand in constructing their search query.
