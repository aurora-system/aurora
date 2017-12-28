<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Search Customer
                </div>
                <div class="panel-body">
                    <form action="">
                        <div class="form-group">
                            <label for="email">Customer Name:</label>
                            <input type="text" class="form-control" id="customername">
                          </div>
                          <div class="form-group">
                            <label for="pwd">Delivery Receipt Number:</label>
                            <input type="text" class="form-control" id="drnumber">
                          </div>
                          <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Search Results
                </div>
                <div class="panel-body">
                    <table id="myTable" class="table table-striped table-bordered table-hover">
                        <thead>
                          <tr>
                            <th>Customer Name</th>
                            <th>Address</th>
                            <th>Contact Number</th>
                            <th>Contact Person</th>
                            <th>Actions</th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script src="resources/jquery.min.js"></script>
    <script src="resources/datatables.min.js"></script>
    <script src="script.js"></script>
    <link rel="stylesheet" href="resources/bootstrap.css"/>
    <link rel="stylesheet" href="resources/datatables.min.css"/>
    <script type="text/javascript">
        $(document).ready(() => {
            $('#myTable').DataTable()
        })
    </script>

</body>
</html>
