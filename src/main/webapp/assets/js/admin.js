var mailApp = angular.module('mailApp', []);
mailApp.controller('index', function ($scope) {
    $scope.emails = [
        {
            'subject': 'Hello',
            'content': 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad hic ipsum quis libero nisi pariatur, quam repellat molestias quibusdam, saepe beatae ducimus reprehenderit aliquid tempore rem, officia similique provident eum!',
            'from': 'm4m4lj@gmail.com'
        },
        {
            'subject': 'Question',
            'content': 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad hic ipsum quis libero nisi pariatur, quam repellat molestias quibusdam, saepe beatae ducimus reprehenderit aliquid tempore rem, officia similique provident eum!',
            'from': 'contact@jahanii.ir'
        },
        {
            'subject': 'Welcome',
            'content': 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad hic ipsum quis libero nisi pariatur, quam repellat molestias quibusdam, saepe beatae ducimus reprehenderit aliquid tempore rem, officia similique provident eum!',
            'from': 'git@bugpedia.ir'
        }
    ];
    $scope.delete = function (elem) {
        var index = $scope.emails.indexOf(elem);
        $scope.emails.splice(index, 1);
    }
});

function selectAll(selectAll) {
    const checkboxes
        = document.querySelectorAll('input[type="checkbox"]');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked
    })
}