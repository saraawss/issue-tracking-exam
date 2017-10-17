angular.module("myapp.controllers", [])
            .controller("myappcontroller", function($scope,$location,$http,$routeParams,DTOptionsBuilder, DTColumnBuilder) {
            
            $scope.formData = {
            	title: '',
            	description: '',
            	status: '',
            	reportUser: '',
            	assignee: '',
            	completed: ''
            };
            
            
            
            $scope.submitData = function (val) {
				
				if(val){
	                var onSuccess = function (data, status, headers, config) {
	                    alert('Issue saved successfully.');
	                    $scope.submit_disabled = true;
	                };
	
	                var onError = function (data, status, headers, config) {
	                    alert('Error occured.');
	                }
	
					var dataObj = {
						title : $scope.formData.title,
						description : $scope.formData.description,
						status : $scope.formData.status,
						reporter : $scope.formData.reportUser,
						assignee : $scope.formData.assignee,
						completed : $scope.formData.completed
					};
	                $http.post('./issue/submitData', dataObj)
	                    .success(onSuccess)
	                    .error(onError);
                }else{
                	$scope.submitUpdateData();
                }

            };
            
            $scope.getcreatepage = function () {
            	$scope.formData.title='';
		    	$scope.formData.description='';
		    	$scope.formData.status='';
		    	$scope.formData.reportUser='';
		    	$scope.formData.assignee='';
		    	$scope.formData.completed='';
            	$scope.submit_disabled = false;
            }
            
            $scope.submitUpdateData = function () {
            
    			var onSuccess = function (data, status, headers, config) {
	                    alert('Issue updated successfully.');
	                };
	
	                var onError = function (data, status, headers, config) {
	                    alert('Error occured when Updating...');
	                }   
	                 	
            	var dataObj = {
            		id : $scope.formData.id,
					title : $scope.formData.title,
					description : $scope.formData.description,
					status : $scope.formData.status,
					reporter : $scope.formData.reportUser,
					assignee : $scope.formData.assignee,
					completed : $scope.formData.completed
				};
				
				var id = $scope.formData.id;
				
                $http.put('./update_issue/'+id, dataObj)
	                    .success(onSuccess)
	                    .error(onError);
            };
            
            $scope.view_report = function () {
            	$http.get('./issue/getData')
	            		.success(function(data, status, headers, config) {
	            	   			
	           		if(status == "200"){
	           		
	           			$scope.reportData = data;
	           			
	            	}
	           })
	           .error(function(data, status, headers, config) {
	            	
	           });
            
     		};
     		
     		$scope.view_user = function () {
            	$http.get('./user/getData')
	            		.success(function(data, status, headers, config) {
	            	   			
	           		if(status == "200"){
	           		
	           			$scope.userData = data;
	           			
	            	}
	           })
	           .error(function(data, status, headers, config) {
	            	
	           });
            
     		};
     		
    		$scope.delete_issue = function (id) {
    		
    			$http.delete('./deleteIssue/'+id)
	            		.success(function(data, status, headers, config) {
	            	   			
	           		if(status == "200"){
	           		
	           			alert("Issue Deleted");
	           			$scope.view_report();
	           			
	            	}
	           })
	           .error(function(data, status, headers, config) {
	            	
	           });
    		};
    		
    		var listId;
    		
    		var loc = $(location).attr('href');
			if (loc.indexOf("?") >= 0) {
			    var url = loc.substr(loc.indexOf("?") + 1, loc.length);
			    listId = url.split("=")[1];
			}
			
        	if(angular.isUndefined(listId)){
        		$scope.update=false;
        		$scope.create=true;
        	}
        	
        	if(!angular.isUndefined(listId)){
 				var param_id = listId;
            	
            	
				$http.get('./updateform/'+param_id)
	            		.success(function(data, status, headers, config) {
	            	   			
	           		if(status == "200"){
	           		
	           			$scope.formData.id=data.id;
	           			$scope.formData.title=data.title;
		    			$scope.formData.description=data.description;
		    			$scope.formData.status=data.status;
		    			$scope.formData.reportUser=data.reporter;
		    			$scope.formData.assignee=data.assignee;
		    			
		    			if(data.completed != null){
			    			var datecompleted=new Date(data.completed);
			    			$scope.formData.completed=datecompleted;
		    			}
		    			$scope.update=true;
		    			$scope.create=false;
		    			
	            	}
	           })
	           .error(function(data, status, headers, config) {
	            	
	           });
  			};
  			
  			$scope.searchData = function () {
					
					if(angular.isUndefined($scope.reporter)){
						alert("Please Select Reporter");
						return;
					}else if(angular.isUndefined($scope.assignee)){
						alert("Please Select Assignee");
						return;
					}else if($scope.fromdate == null){
						alert("Please Select From Date");
						return;
					}else if($scope.enddate == null){
						alert("Please Select End Date");
						return;
					}
					
					var dataObj = {
						reporter : $scope.reporter,
						assignee : $scope.assignee,
						fromdate : $scope.fromdate,
						enddate : $scope.enddate
					};
	                   
	               $http.post('./issue/search', dataObj)
	            		.success(function(data, status, headers, config) {
	            	   			
	           		if(status == "200"){
	           		
	           			$scope.reportData = data;
	           			
	            	}
	           })
	           .error(function(data, status, headers, config) {
	            	
	           });

            };
            
            $scope.formData.userData = [
	        {id : 1, username : "j.jones"},
	        {id : 2, username : "h.humble"}
	        ];
  				    
            $scope.vm = {};

            $scope.vm.dtOptions = DTOptionsBuilder.newOptions().withOption('order', [0, 'asc']);
    
            $scope.view_report();
           
            
            	
});
