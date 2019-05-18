import React, {Component} from 'react';
import stockService from '../service/stock.service'

export default class Edit extends Component {
    constructor(props) {
        super(props);
        this.onChangeStockName = this.onChangeStockName.bind(this);
        this.onChangeStockPrice = this.onChangeStockPrice.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            stockName: '',
            stockPrice: ''
        }
    }

    // Retrieve stock data
    componentDidMount() {
        stockService.getStockById(this.props.match.params.id).then(response => {
            this.setState({
                stockName: response.data.stockName,
                stockPrice: response.data.stockPrice
            })
        }).catch(function (error) {
            console.log(error);
            this.setState({hasErrors: true, message: error.response.data.errorMessage})
        });
    }

    onChangeStockName(e) {
        this.setState({
            stockName: e.target.value
        });
    }

    onChangeStockPrice(e) {
        this.setState({
            stockPrice: e.target.value
        })
    }

    // Send an API request to update stock on form submit
    onSubmit(e) {
        e.preventDefault();
        const request = {
            stockName: this.state.stockName,
            stockPrice: this.state.stockPrice
        };

        stockService.updateStock(this.props.match.params.id, request)
            .then(() => this.props.history.push('/index'))
            .catch(error => {
                console.log(error.response);
                this.setState({hasErrors: true, message: error.response.data.errorMessage})
            });
    }

    render() {
        let error;

        // Render errors
        if (this.state.hasErrors) {
            error = <div className="alert alert-danger">
                <strong>Error!</strong> {this.state.message}.
            </div>;
        }

        return (
            <div style={{marginTop: 10}}>
                <h3 align="center">Update Stock</h3>
                {error}
                <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label>Stock Name: </label>
                        <input
                            type="text"
                            className="form-control"
                            value={this.state.stockName}
                            onChange={this.onChangeStockName}
                        />
                    </div>
                    <div className="form-group">
                        <label>Stock Price: </label>
                        <input type="text"
                               className="form-control"
                               value={this.state.stockPrice}
                               onChange={this.onChangeStockPrice}
                        />
                    </div>
                    <div className="form-group">
                        <input type="submit"
                               value="Update Stock"
                               className="btn btn-primary"/>
                    </div>
                </form>
            </div>
        );
    }
}
