angular.module('AmazonApp').factory('AccountFactory', function ($http, $q, $location) {

    var acctFactory = {};
    
    acctFactory.createAccount = function($scope) {
        console.log("createAccount()");
        var ccTypeData = {"creditCardTypeName" : $scope.ccTypeName};
        var ccTypePromise = createCreditCardType(ccTypeData);
        ccTypePromise.then(function(data) {
            $scope.creditCardType = data;
            console.log("CreditCardType: " + $scope.creditCardType.creditCardTypeId);
            
            var creditCardData = 
                     {"creditCardNumber": $scope.creditCardNumber,
                      "creditCardType" : 
                      {"creditCardTypeId" : $scope.creditCardType.creditCardTypeId,
                       "creditCardTypeName" : $scope.creditCardType.creditCardTypeName},
                      "firstname": $scope.firstname,
                      "lastname" : $scope.lastname,
                      "expiryDate" : $scope.expiryDate,
                      "securityCode" : $scope.securityCode};

            var ccPromise = createCreditCard(creditCardData);
            ccPromise.then(function(data) {
                $scope.creditCard = data;

                var accountData = 
                    {"username" : $scope.username,
                     "firstname": $scope.firstname,
                     "lastname" : $scope.lastname,
                     "password" : $scope.password,
                     "creditCard" : {"creditCardId" : $scope.creditCard.creditCardId,
                                     "creditCardNumber" : $scope.creditCard.creditCardNumber,
                                     "creditCardType" :
                                     {"creditCardTypeId": $scope.creditCard.creditCardType.creditCardTypeId,
                                      "creditCardTypeName": $scope.creditCard.creditCardType.creditCardTypeName},
                                      "firstname" : $scope.creditCard.firstname,
                                      "lastname" : $scope.creditCard.lastname,
                                      "expiryDate" : $scope.creditCard.expiryDate,
                                      "securityCode" : $scope.creditCard.securityCode
                                    }
                    };

                var acctPromise = constructAccount(accountData);
                acctPromise.then(function(data) {
                    console.log("Account created for " + data.username);
                    $location.path('/avatar').search({username : data.username});
                });
            });
        });
    };
    
    createCreditCardType = function(ccTypeData) {
        console.log("createCreditCardType()");
        var ccTypeUrl =
            'http://localhost:8080/profile/creditcardtype';
        console.log("ccTypeUrl " + ccTypeUrl);
        var defer = $q.defer();
        $http.post(ccTypeUrl, ccTypeData).then(function(response) {
            defer.resolve(response.data);
        });
        return defer.promise;
    };

    createCreditCard = function(creditCardData) {
        console.log("createCreditCard()");
        var ccUrl = 'http://localhost:8080/profile/creditcard';
        var defer = $q.defer();
        $http.post(ccUrl, creditCardData).success(function(data) {
            console.log("Retrieve newly created CreditCard....");
            var getCCUrl = 'http://localhost:8080/profile/creditcard/' + data.creditCardId;
            $http.get(getCCUrl).then(function(response) {
                defer.resolve(response.data);
            });
        });
        return defer.promise;
    };

    constructAccount = function(accountData) {
        console.log("constructAccount()");
        var defer = $q.defer();                                      
        var accountUrl = 'http://localhost:8080/profile/account';
        $http.post(accountUrl, accountData).then(function(response) {
            defer.resolve(response.data);
        });
        return defer.promise;
    };

    acctFactory.validateUsername = function (username) {
        console.log("validateUsername()");
        var accountUrl = 'http://localhost:8080/profile/account/' + username;
        var defer = $q.defer();
        $http.get(accountUrl).
            success(function(data) {
                console.log("data.username: " + data.username);
            if (data.username == 'Username not found') {
                    console.log("Invalid username");
                    defer.reject();
                } else {
                    console.log("Valid username");
                    defer.resolve();
                }
            });
        return defer.promise;
    };
    
    acctFactory.uniqueUsername = function (username) {
        console.log("uniqueUsername()");
        var accountUrl = 'http://localhost:8080/profile/account/' + username;
        var defer = $q.defer();
        $http.get(accountUrl).
        success(function(data) {
            console.log("data.username: " + data.username);
            if (data.username == 'Username not found') {
                console.log("Username is available for new account");
                defer.resolve();
            } else {
                console.log("Username is already in use and not available for new account");
                defer.reject();
            }
        });
        return defer.promise;
    };
    return acctFactory;
});
