fetch('/products/stats')
    .then(response => response.json())
    .then(data => {
        var inStockCount = data['in_stock'];
        var outOfStockCount = data['out_of_stock'];
        var onCommandCount = data['on_command'];

        var ctx = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['In_Stock', 'Out_of_Stock', 'On_Command'],
                datasets: [{
                    label: 'Product Counts',
                    data: [inStockCount, outOfStockCount, onCommandCount],
                    backgroundColor: [
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(255, 206, 86, 0.2)'
                    ],
                    borderColor: [
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 99, 132, 1)',
                        'rgba(255, 206, 86, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    });